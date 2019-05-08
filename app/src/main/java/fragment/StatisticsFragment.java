package fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xchallenge.R;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

import adapter.PersonAdapter;
import bean.Person;


public class StatisticsFragment extends Fragment {

    public static Fragment newInstance() {
        return new StatisticsFragment();
    }

    private List<Person> personList = new ArrayList<>();


    RecyclerView mRecyclerView;

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        initPeople();
        FragmentManager fm = getFragmentManager();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new PersonAdapter(fm,personList));

    }

    private void initPeople() {
        personList.clear();
        Person tony = new Person("Tony", R.drawable.person_tony, "在线");
        personList.add(tony);
        Person tom = new Person("Tom", R.drawable.person_tom, "在线");
        personList.add(tom);
        Person ella = new Person("Ella", R.drawable.person_ella, "在线");
        personList.add(ella);
        personList.add(ella);
        personList.add(ella);
        personList.add(ella);
        personList.add(ella);
    }

}
