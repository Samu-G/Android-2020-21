package com.unito.ium.progetto;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unito.ium.progetto.db.Database;

/**
 * A fragment representing a list of Items.
 */
public class CatalogoCorsiFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CatalogoCorsiFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CatalogoCorsiFragment newInstance(int columnCount) {
        CatalogoCorsiFragment fragment = new CatalogoCorsiFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogo_corsi_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
      /*      new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    recyclerView.setAdapter(new MyCorsiRecyclerViewAdapter(Database.getInstance(getContext()).getCorsoDao().findAll()));
                    return null;
                }
            }.execute();
*/
            Handler refresh = new Handler(Looper.getMainLooper());
            refresh.post(new Runnable() {
                public void run()
                {
                    recyclerView.setAdapter(new MyCorsiRecyclerViewAdapter(
                            Database.getInstance(getContext())
                                    .getCorsoDao()
                                    .findAll()));
                }
            });


        }
        return view;
    }
}