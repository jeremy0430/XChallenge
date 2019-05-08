package fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xchallenge.R;

import adapter.PersonAdapter;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class MyAlertDialogFragment extends DialogFragment {
    public MyAlertDialogFragment() {
    }

    public static MyAlertDialogFragment newInstance(String title,int imageId) {
        MyAlertDialogFragment fragment = new MyAlertDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("imageId",imageId);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_dialog, container, false);
        int  imageId = getArguments().getInt("imageId");
        ImageView imageView = (ImageView) view.findViewById(R.id.picture);
        imageView.setBackgroundDrawable(getResources().getDrawable(imageId));
        return view;
    }


//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        String title = getArguments().getString("title");
//        int  imageId = getArguments().getInt("imageId");
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        builder.setTitle(title);
//        builder.setIcon(getResources().getDrawable(imageId));
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getContext(), "确定", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
//            }
//        });
//        return builder.create();
//    }
}