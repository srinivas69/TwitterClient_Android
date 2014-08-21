package com.seenu.twitterclient;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TwitterHomeTimelineAdapter extends BaseAdapter {

	private Context context;
	private List<HomeTimelineObject> result;

	public TwitterHomeTimelineAdapter(Context context,
			List<HomeTimelineObject> result) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.result = result;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return result.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return result.get(pos);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder = null;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (view == null) {
			view = inflater.inflate(R.layout.listview_item_home_timeline, null);
			holder = new ViewHolder();

			holder.imageViewAdpIv = (ImageView) view
					.findViewById(R.id.imageView1);
			holder.nameAdpTv = (TextView) view.findViewById(R.id.textView1);
			holder.usrNameAdpTv = (TextView) view.findViewById(R.id.textView2);
			holder.descAdpTv = (TextView) view.findViewById(R.id.textView3);
			view.setTag(holder);
		} else
			holder = (ViewHolder) view.getTag();
		
		return view;
	}

	private static class ViewHolder {

		private ImageView imageViewAdpIv;
		private TextView nameAdpTv;
		private TextView usrNameAdpTv;
		private TextView descAdpTv;
	}

}
