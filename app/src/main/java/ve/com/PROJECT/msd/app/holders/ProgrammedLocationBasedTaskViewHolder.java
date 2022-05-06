package ve.com.PROJECT.msd.app.holders;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ve.com.PROJECT.msd.R;
import ve.com.PROJECT.msd.app.activities.TaskDetailActivity;
import ve.com.PROJECT.msd.app.adapters.HomeAdapter;
import ve.com.PROJECT.msd.app.interfaces.ViewHolderClickListener;
import ve.com.PROJECT.msd.enums.AttachmentType;
import ve.com.PROJECT.msd.enums.ReminderType;
import ve.com.PROJECT.msd.model.Task;
import ve.com.PROJECT.msd.model.attachment.Attachment;
import ve.com.PROJECT.msd.model.reminder.LocationBasedReminder;


public class ProgrammedLocationBasedTaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private HomeAdapter mAdapter;
    private Fragment mFragment;

    //UI
    private RelativeLayout mContainer;
    private ImageView mCategoryIcon;

    private ImageView mAttachmentList;
    private ImageView mAttachmentLink;
    private ImageView mAttachmentAudio;
    private ImageView mAttachmentImage;
    private ImageView mAttachmentText;
    
    private TextView mTitle;
    private TextView mDescription;
    private TextView mLocation;

    private View mItemDecoration;

    private ViewHolderClickListener mClickListener;

    //DATA
    private Task mCurrent;
    private int mReminderPosition;

    public ProgrammedLocationBasedTaskViewHolder(View itemView, ViewHolderClickListener listener) {
        super(itemView);

        mClickListener = listener;

        mContainer = (RelativeLayout) itemView.findViewById(R.id.item_task_programmed_location_based_container);
        mCategoryIcon = (ImageView) itemView.findViewById(R.id.item_task_programmed_location_based_category_icon);

        mAttachmentList = (ImageView) itemView.findViewById(R.id.item_task_programmed_location_based_attachment_list);
        mAttachmentLink = (ImageView) itemView.findViewById(R.id.item_task_programmed_location_based_attachment_link);
        mAttachmentAudio = (ImageView) itemView.findViewById(R.id.item_task_programmed_location_based_attachment_audio);
        mAttachmentImage = (ImageView) itemView.findViewById(R.id.item_task_programmed_location_based_attachment_image);
        mAttachmentText = (ImageView) itemView.findViewById(R.id.item_task_programmed_location_based_attachment_text);

        mTitle = (TextView) itemView.findViewById(R.id.item_task_programmed_location_based_title);
        mDescription = (TextView) itemView.findViewById(R.id.item_task_programmed_location_based_description);

        mLocation = (TextView) itemView.findViewById(R.id.item_task_programmed_location_based_location);
        mItemDecoration = itemView.findViewById(R.id.item_task_programmed_location_item_decoration);

    }


    public void setData(HomeAdapter adapter, Fragment fragment, Task current, int position, boolean isSelected, boolean nextItemIsATask) {
        mAdapter = adapter;
        mFragment = fragment;
        mCurrent = current;
        mReminderPosition = position;

        mCategoryIcon.setImageResource(mCurrent.getCategory().getIconRes());

        mContainer.setBackgroundColor((isSelected ? ContextCompat.getColor(fragment.getActivity(), R.color.gray_300) : Color.TRANSPARENT ));

        mAttachmentList.setColorFilter(ContextCompat.getColor(mFragment.getActivity(), (hasAttachmentsOfType(AttachmentType.LIST) ? R.color.icons_enabled : R.color.icons_disabled)));
        mAttachmentLink.setColorFilter(ContextCompat.getColor(mFragment.getActivity(), (hasAttachmentsOfType(AttachmentType.LINK) ? R.color.icons_enabled : R.color.icons_disabled)));
        mAttachmentAudio.setColorFilter(ContextCompat.getColor(mFragment.getActivity(), (hasAttachmentsOfType(AttachmentType.AUDIO) ? R.color.icons_enabled : R.color.icons_disabled)));
        mAttachmentImage.setColorFilter(ContextCompat.getColor(mFragment.getActivity(), (hasAttachmentsOfType(AttachmentType.IMAGE) ? R.color.icons_enabled : R.color.icons_disabled)));
        mAttachmentText.setColorFilter(ContextCompat.getColor(mFragment.getActivity(), (hasAttachmentsOfType(AttachmentType.TEXT) ? R.color.icons_enabled : R.color.icons_disabled)));

        mTitle.setText(mCurrent.getTitle());
        if(!mCurrent.getDescription().isEmpty())
            mDescription.setText(mCurrent.getDescription());
        else
            mDescription.setText("");

        if(current.getReminderType() == ReminderType.LOCATION_BASED && current.getReminder() != null) {
            mLocation.setText(((LocationBasedReminder)current.getReminder()).getPlace().getAddress());
        } else {
            mLocation.setText("-");
        }

        mItemDecoration.setVisibility(nextItemIsATask ? View.VISIBLE : View.INVISIBLE);
    }


    private boolean hasAttachmentsOfType(AttachmentType attachmentType) {
        for (Attachment attachment : mCurrent.getAttachments()) {
            if(attachment.getType().equals(attachmentType))
                return true;
        }
        return false;
    }

    public void setListeners() {
        mContainer.setOnClickListener(this);
        mContainer.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.item_task_programmed_location_based_container:
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(mCategoryIcon, mFragment.getResources().getString(R.string.transition_task_list_category));
                //pairs[1] = new Pair<View, String>(mTitle, mFragment.getResources().getString(R.string.transition_task_list_title));
                //pairs[2] = new Pair<View, String>(mDescription, mFragment.getResources().getString(R.string.transition_task_list_description));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mFragment.getActivity(), pairs);

                Intent openTaskDetailActivity = new Intent(mFragment.getActivity(), TaskDetailActivity.class);
                openTaskDetailActivity.putExtra(TaskDetailActivity.TASK_ID_TO_DISPLAY, mCurrent.getId());
                openTaskDetailActivity.putExtra(TaskDetailActivity.TASK_POSITION, mReminderPosition);
                //mFragment.getActivity().startActivityForResult(openTaskDetailActivity, TaskDetailActivity.TASK_DETAIL_REQUEST_CODE, options.toBundle());

                if (mClickListener != null) {
                    mClickListener.onItemClicked(mReminderPosition, openTaskDetailActivity, options.toBundle());
                }

                break;
        }
    }

    @Override
    public boolean onLongClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.item_task_programmed_location_based_container:
                if (mClickListener != null) {
                    mClickListener.onItemLongClicked(mReminderPosition);
                }
                break;
        }

        return false;
    }
}
