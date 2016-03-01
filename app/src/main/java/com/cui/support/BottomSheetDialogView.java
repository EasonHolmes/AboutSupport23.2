package com.cui.support;


import android.app.Activity;
import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BottomSheetDialogView {


    private static String[] sStringList;

    static {
        sStringList = new String[50];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sStringList.length; i++) {
            stringBuilder.append(i + 1);
            sStringList[i] = stringBuilder.toString();
        }
    }

    /**
     * remember to call setLocalNightMode for dialog
     *
     * @param context
     * @param dayNightMode current day night mode
     */
    public static void show_BottomSheetDialogView(Context context, int dayNightMode) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.getDelegate().setLocalNightMode(dayNightMode);
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_dialog_recycler_view, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.bottom_sheet_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new SimpleAdapter());
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.show();
    }

    //    STATE_COLLAPSED
//    折叠状态。可通过app:behavior_peekHeight来设置默认显示的高度。
//
//    STATE_SETTING
//    拖拽松开之后到达终点位置（collapsed or expanded）前的状态。
//
//    STATE_EXPANDED
//    完全展开的状态。
//
//    STATE_HIDDEN
//    隐藏状态。默认是false，可通过app:behavior_hideable属性设置。
//
//    STATE_DRAGGING
//            被拖拽状态
    public static void show_BottomView(Context mContext, View view, RecyclerView recyclerView) {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(view);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                String state = "null";
                switch (newState) {
                    case 1:
                        state = "STATE_DRAGGING";
                        break;
                    case 2:
                        state = "STATE_SETTLING";
                        break;
                    case 3:
                        state = "STATE_EXPANDED";
                        break;
                    case 4:
                        state = "STATE_COLLAPSED";
                        break;
                    case 5:
                        state = "STATE_HIDDEN";
                        break;
                }
                Log.d("MainActivity", "newState:" + state);
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
//                Log.d("MainActivity", "slideOffset:" + slideOffset);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new SimpleAdapter());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_text_view);
        }
    }

    public static class SimpleAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_item, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText(sStringList[position]);
        }

        @Override
        public int getItemCount() {
            return sStringList.length;
        }
    }
}
