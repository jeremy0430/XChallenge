package adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xchallenge.R;

import java.util.List;

import bean.Person;
import fragment.MyAlertDialogFragment;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.ViewHolder> {
    private Context mContext;
    private List<Person> mPersonList;
    private AdapterView.OnItemClickListener listener;
    private FragmentManager FragManager;



    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView personImage;
        TextView personName;
        TextView personStatus;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            personImage = (ImageView) view.findViewById(R.id.person_image);
            personName = (TextView) view.findViewById(R.id.person_name);
            personStatus = (TextView) view.findViewById(R.id.person_status);
        }
    }

    public StatisticsAdapter(FragmentManager getSupportFragmentManager, List<Person> personList) {
        mPersonList = personList;
        FragManager = getSupportFragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.person_statistics_item,
                parent, false);


        final ViewHolder holder = new ViewHolder(view);
        holder.personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int	 position	=	holder.getAdapterPosition();
                Person person = mPersonList.get(position);
                MyAlertDialogFragment fragment = MyAlertDialogFragment.newInstance("他的样子",person.getImageId());
                fragment.show(FragManager, "myAlert");
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = mPersonList.get(position);

        holder.personName.setText(person.getName());
        holder.personStatus.setText(person.getStatus());
        Glide.with(mContext).load(person.getImageId()).into(holder.personImage);
    }

    @Override
    public int getItemCount() {
        return mPersonList.size();
    }
}