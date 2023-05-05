package com.test.a360camera;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.test.a360camera.cameraview.AspectRatio;
import com.test.a360camera.cameraview.Size;
import com.test.a360camera.cameraview.SizeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class PictureDimenFragment extends DialogFragment {
    private static ArrayList<Size> gSizes;
    private static Size gCurrentSize;

    public interface Listener {
        void onPicDimenSelected(@NonNull Size size);
    }
    private Listener mListener;

    public static PictureDimenFragment newInstance(Object[] sizes,
                                                   android.util.Size currentSize) {
        final PictureDimenFragment fragment = new PictureDimenFragment();

        gSizes = new ArrayList<>();
        int len = sizes.length;
        for (int i = 0; i < len; i++) {
            gSizes.add(new Size(((Size) sizes[i]).getWidth(), ((Size) sizes[i]).getHeight()));
        }

        gCurrentSize = new Size(currentSize.getWidth(), currentSize.getHeight());

//        final Bundle args = new Bundle();
//        args.putParcelableArray(ARG_ASPECT_RATIOS, );
//        args.putParcelable(ARG_CURRENT_ASPECT_RATIO, currentRatio);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (Listener) context;
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle args = getArguments();
//        final AspectRatio[] ratios = (AspectRatio[]) args.getParcelableArray(ARG_ASPECT_RATIOS);
//        if (ratios == null) {
//            throw new RuntimeException("No ratios");
//        }
//        Arrays.sort(ratios);
//        final AspectRatio current = args.getParcelable(ARG_CURRENT_ASPECT_RATIO);

        if (gSizes == null) {
            throw new RuntimeException("No sizes");
        }

        final PictureDimenAdapter adapter = new PictureDimenAdapter(gSizes, gCurrentSize);
        return new AlertDialog.Builder(getActivity())
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        mListener.onPicDimenSelected(gSizes.get(position));
                    }
                })
                .create();
    }

    private static class PictureDimenAdapter extends BaseAdapter {

        private final ArrayList<Size> mSizes;
        private final Size mCurrentRatio;

        PictureDimenAdapter(ArrayList<Size> ratios, Size current) {
            mSizes = ratios;
            mCurrentRatio = current;
        }

        @Override
        public int getCount() {
            return mSizes.size();
        }

        @Override
        public Size getItem(int position) {
            return mSizes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).hashCode();
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1, parent, false);
                holder = new ViewHolder();
                holder.text = view.findViewById(android.R.id.text1);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            Size ratio = getItem(position);
            StringBuilder sb = new StringBuilder(ratio.toString());
            if (ratio.equals(mCurrentRatio)) {
                sb.append(" *");
            }
            holder.text.setText(sb);
            return view;
        }

        private static class ViewHolder {
            TextView text;
        }

    }
}
