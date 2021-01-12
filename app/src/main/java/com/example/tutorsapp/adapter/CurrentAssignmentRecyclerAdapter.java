package com.example.tutorsapp.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.RequestModel;
import com.example.tutorsapp.models.RequestedAssignmentModel;
import com.example.tutorsapp.models.UserInfo;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.ui.TuitionAssignmentRequestActivity;
import com.example.tutorsapp.utils.Validations;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class CurrentAssignmentRecyclerAdapter extends RecyclerView.Adapter<CurrentAssignmentRecyclerAdapter.RequestViewHolder> {
    List<RequestModel> searchResultList;
    final int HEAD = 0, CONTENT = 1;
    Activity activity;

    public CurrentAssignmentRecyclerAdapter(List<RequestModel> list, Activity activity) {
        this.searchResultList = list;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return HEAD;
        } else {
            return CONTENT;
        }
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType == HEAD ? R.layout.li_current_request_head : R.layout.li_current_request_content, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        if(getItemViewType(position) == HEAD) {

        } else {
            RequestModel requestModel = searchResultList.get(position - 1);
            holder.setData(requestModel);
            holder.tvShowDetails.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.startActivity(new Intent(activity, TuitionAssignmentRequestActivity.class).putExtra(TuitionAssignmentRequestActivity.JOB_ID, requestModel.getRequestID()));
                }
            });
            holder.tvStatus.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(requestModel.getRequestStatus().equals("0")) {
                        showActionDialog(requestModel);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(searchResultList == null || searchResultList.size() == 0) {
            return 0;
        }
        return searchResultList.size() + 1;
    }

    static class RequestViewHolder extends RecyclerView.ViewHolder {

        TextView tvDandT;
        TextView tvAssign;
        TextView tvShowDetails;
        TextView tvStatus;

        public RequestViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvDandT = itemView.findViewById(R.id.tvDAndT);
            tvAssign = itemView.findViewById(R.id.tvAssign);
            tvShowDetails = itemView.findViewById(R.id.tvShowDetails);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }

        void setData(RequestModel result) {
            tvDandT.setText(result.getReqestFromName());
            tvAssign.setText(result.getReqestToName());
//            tvShowDetails.setText(R.string.show_details_text);
            if(Validations.isNotEmpty(result.getRequestStatus())) {
                if (result.getRequestStatus().equals("1")) {
                    tvStatus.setText("Accepted");
                } else if (result.getRequestStatus().equals("2")){
                    tvStatus.setText("Rejected");
                } else {
                    tvStatus.setText("In process");
                }
            } else {
                tvStatus.setText("In process");
            }


        }

    }

    private void showActionDialog(RequestModel result) {

        new AlertDialog.Builder(activity)
                .setTitle("Status update?")
                .setMessage("You have upcoming request, please choose action for request.")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton("Accept/Approve", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        updateToServer("1", result);
                        dialog.dismiss();
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Reject", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateToServer("2", result);
                    }
                })
                .setCancelable(true)
                .show();
    }

    private void updateToServer(String status, RequestModel result){
        SharedPreferences mPrefs = activity.getSharedPreferences(Constants.dataPrefsKey, MODE_PRIVATE);
        Gson gsonn = new Gson();
        String jsnon = mPrefs.getString(Constants.userLoginKey, "");
        UserInfo userInfo = gsonn.fromJson(jsnon, UserInfo.class);
        RequestModel requestModel = new RequestModel();
        requestModel.setUserInfo(userInfo);
        requestModel.setRequestID(result.getRequestID());
        requestModel.setChannelID(0);
        requestModel.setRequestStatus(status);
        APIManager.getInstance().acceptRejectRequest(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if(((GeneralResponse) response.body()).getIsSuccess()) {
                    Toast.makeText(activity, "Status updated", Toast.LENGTH_SHORT).show();
                    result.setRequestStatus(status);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(activity, "Status couldn't updated", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(activity, "Status couldn't updated", Toast.LENGTH_SHORT).show();
            }
        }, requestModel);

    }

    public void updateList(List<RequestModel> requestModels) {
        searchResultList = requestModels;
        notifyDataSetChanged();
    }
}
