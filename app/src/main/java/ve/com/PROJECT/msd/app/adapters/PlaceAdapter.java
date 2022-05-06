package ve.com.PROJECT.msd.app.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;

import java.util.List;

import ve.com.PROJECT.msd.R;
import ve.com.PROJECT.msd.app.holders.PlaceViewHolder;
import ve.com.PROJECT.msd.model.Place;



public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    //DATA
    private List<Place> mPlaces;
    private LayoutInflater mInflater;
    private Activity mActivity;

    public PlaceAdapter(Activity activity, List<Place> places) {
        mPlaces = places;
        mActivity = activity;
        mInflater = LayoutInflater.from(activity);

    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlaceViewHolder(mInflater.inflate(R.layout.list_item_place, parent, false));
    }


    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {
        holder.setData(this, mActivity, mPlaces.get(position), position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }
}
