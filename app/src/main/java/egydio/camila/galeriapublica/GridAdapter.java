package egydio.camila.galeriapublica;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.text.SimpleDateFormat;

public class GridAdapter extends PagingDataAdapter<ImageData, MyViewHolder> {

        public GridAdapter(DiffUtil.@NonNull ItemCallback<ImageData> diffCallback) {
            super(diffCallback);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item, parent,
                    false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            ImageData imageData = getItem(position);

            Bitmap thumb = imageData.thumb;
            ImageView imageView = holder.itemView.findViewById(R.id.img_grid);
            imageView.setImageBitmap(thumb);
        }
}
