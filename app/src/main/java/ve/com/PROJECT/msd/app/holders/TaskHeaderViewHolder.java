package ve.com.PROJECT.msd.app.holders;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ve.com.PROJECT.msd.R;
import ve.com.PROJECT.msd.app.adapters.HomeAdapter;



public class TaskHeaderViewHolder extends RecyclerView.ViewHolder {

    //UI
    private TextView mHeaderTitle;

    //DATA
    private HomeAdapter mAdapter;
    private Fragment mFragment;
    private int mPosition;

    public TaskHeaderViewHolder(View itemView) {
        super(itemView);

        mHeaderTitle = (TextView) itemView.findViewById(R.id.item_task_header_title);
    }

    public void setData(HomeAdapter adapter, Fragment fragment, String title, boolean headerTitleRed, int position) {
        mAdapter = adapter;
        mFragment = fragment;
        mPosition = position;

        mHeaderTitle.setText(title);
        if(headerTitleRed)
            mHeaderTitle.setTextColor(ContextCompat.getColor(mFragment.getActivity(), R.color.header_title_red));
        else
            mHeaderTitle.setTextColor(ContextCompat.getColor(mFragment.getActivity(), R.color.primary));
    }
}
