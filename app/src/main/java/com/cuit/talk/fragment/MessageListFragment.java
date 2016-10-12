package com.cuit.talk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cuit.talk.activity.R;
import com.cuit.talk.adapter.MessageListRecyviewAdapter;
import com.cuit.talk.dependen.DividerItemDecoration;
import com.cuit.talk.entity.Message;
import com.cuit.talk.entity.MessageSimple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inori on 16/10/9.
 */

public class MessageListFragment extends Fragment {

    private MessageListRecyviewAdapter adapter;

    private List<MessageSimple> messageSimpleList;

    private RecyclerView recyclerView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_list_layout,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_message_list_rv);

        /**
         * 使recyclerView保持固定大小 这样会提高RecyclerView的性能
         */
        recyclerView.setHasFixedSize(true);

        /**
         * LinearLayoutManager，如果你需要显示的是横向滚动的列表或者竖直滚动的列表，
         * 则使用这个LayoutManager。显然，我们要实现的是ListView的效果，所以需要使用它。
         * 生成这个LinearLayoutManager之后可以设置他滚动的方向，默认竖直滚动，所以这里没有显式地设置。
         */
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        initData();

        adapter = new MessageListRecyviewAdapter(messageSimpleList);

        adapter.setOnRecyclerViewListener(new MessageListRecyviewAdapter.OnRecyclerViewListener() {
            @Override
            public void onItemClick(Context context, int position) {
                Toast.makeText(container.getContext(), "长按可以删除哦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(Context context, int position) {
                adapter.notifyItemRemoved(position);
                messageSimpleList.remove(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
                return false;
            }
        });

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(
                view.getContext(), DividerItemDecoration.VERTICAL_LIST));
        return view;
    }

    private void initData(){
        messageSimpleList = new ArrayList<MessageSimple>();
        for (int i = 0; i < 30; ++i) {
            MessageSimple messageSimple = new MessageSimple();
            messageSimpleList.add(messageSimple);
        }


    }
}