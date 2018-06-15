package com.vaikunj.myinerview_app.adapter;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vaikunj.myinerview_app.Pojo.DsModel;

import com.vaikunj.myinerview_app.R;
import com.vaikunj.myinerview_app.utils.MyUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionAnswerAdapter extends RecyclerView.Adapter<QuestionAnswerAdapter.ViewHolder> {

    private ArrayList<DsModel> questionsArrayList;
    Activity mActivity;

    public QuestionAnswerAdapter(Activity mActivity, ArrayList<DsModel> questionsArrayList) {
        this.questionsArrayList = questionsArrayList;
        this.mActivity = mActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_answer, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.question.setText(questionsArrayList.get(position).getQuestion());
            holder.answer.setText(questionsArrayList.get(position).getAnswer());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.cardView2.getVisibility()==View.VISIBLE){
                        MyUtils.collapse(holder.cardView2);
                        MyUtils.collapse(holder.answerfab);
                    }else{
                        MyUtils.expand(holder.cardView2);
                        MyUtils.expand(holder.answerfab);
                    }
                }
            });


    }

    @Override
    public int getItemCount() {
        Log.e("size",""+questionsArrayList.size());
        return questionsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView question;
        View viewMobile;
        TextView viewanswer;
        CardView cardView;
        ImageView answerimageView;
        TextView answer;
        CardView cardView2;
        FloatingActionButton questionfab;
        FloatingActionButton answerfab;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            question=(TextView)itemView.findViewById(R.id.question);
            answer=(TextView)itemView.findViewById(R.id.answer);
            viewanswer=(TextView)itemView.findViewById(R.id.viewanswer);
            cardView=(CardView)itemView.findViewById(R.id.card_view);
            cardView2=(CardView)itemView.findViewById(R.id.card_view2);
            questionfab=(FloatingActionButton) itemView.findViewById(R.id.questionfab);
            answerfab=(FloatingActionButton)itemView.findViewById(R.id.answerfab);
        }
    }
}
