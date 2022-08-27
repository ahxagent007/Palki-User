package com.alphacuetech.xian.palki_drive.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alphacuetech.xian.palki_drive.ItemClickListener;
import com.alphacuetech.xian.palki_drive.R;

import com.alphacuetech.xian.palki_drive.DataModel.Notification;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView RV_notifications;
    CustomAdapterNotificationRV customAdapterNotificationRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        RV_notifications = findViewById(R.id.RV_notifications);

        ArrayList<Notification> notifications = null;

        customAdapterNotificationRV = new CustomAdapterNotificationRV(notifications);
        RV_notifications.setAdapter(customAdapterNotificationRV);

    }

    public class CustomAdapterNotificationRV extends RecyclerView.Adapter<CustomAdapterNotificationRV.ViewHolder> {

        private ArrayList<Notification> notifications;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
            private TextView TV_date, TV_status, TV_min, TV_max, TV_morn, TV_day, TV_eve, TV_night;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                /*TV_date = (TextView) view.findViewById(R.id.TV_date);
                TV_status = (TextView) view.findViewById(R.id.TV_status);
                TV_min = (TextView) view.findViewById(R.id.TV_min);
                TV_max = (TextView) view.findViewById(R.id.TV_max);
                TV_morn = (TextView) view.findViewById(R.id.TV_morn);
                TV_day = (TextView) view.findViewById(R.id.TV_day);
                TV_eve = (TextView) view.findViewById(R.id.TV_eve);
                TV_night = (TextView) view.findViewById(R.id.TV_night);*/

                view.setOnClickListener(this);
                view.setOnLongClickListener(this);

            }

            private ItemClickListener clickListener;

            public void setClickListener(ItemClickListener itemClickListener) {
                this.clickListener = itemClickListener;
            }

            @Override
            public void onClick(View view) {
                clickListener.onClick(view, getPosition(), false);

            }

            @Override
            public boolean onLongClick(View view) {
                clickListener.onClick(view, getPosition(), true);
                return true;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         */
        public CustomAdapterNotificationRV(ArrayList<Notification> notifications) {
            this.notifications = notifications;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.single_notification, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            /*viewHolder.TV_date.setText(convertSecToDate(daily.get((position)).getDt(), TIME_ZONE));
            viewHolder.TV_status.setText(daily.get((position)).getWeather().get(0).getDescription());
            viewHolder.TV_min.setText(convertKelvinToCelsius(daily.get((position)).getTemp().getMin()));
            viewHolder.TV_max.setText(convertKelvinToCelsius(daily.get((position)).getTemp().getMax()));
            viewHolder.TV_morn.setText(convertKelvinToCelsius(daily.get((position)).getTemp().getMorn()));
            viewHolder.TV_day.setText(convertKelvinToCelsius(daily.get((position)).getTemp().getDay()));
            viewHolder.TV_eve.setText(convertKelvinToCelsius(daily.get((position)).getTemp().getEve()));
            viewHolder.TV_night.setText(convertKelvinToCelsius(daily.get((position)).getTemp().getNight()));*/

            viewHolder.setClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, final int position, boolean isLongClick) {


                    if (isLongClick) {

                    } else {

                    }
                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return notifications.size();
        }
    }


}