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

public class ListAdapter extends PagingDataAdapter<ImageData, MyViewHolder> {

    public ListAdapter(DiffUtil.@NonNull ItemCallback<ImageData> diffCallback) {
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

        TextView tvName = holder.itemView.findViewById(R.id.tvName);
        tvName.setText(imageData.fileName);

        TextView tvDate = holder.itemView.findViewById(R.id.tvDate);
        tvDate.setText("Data: " + new SimpleDateFormat("HH:mm dd/MM/yyyy").format(imageData.date));

        TextView tvSize = holder.itemView.findViewById(R.id.tvSize);
        tvSize.setText("Tamanho: " + String.valueOf(imageData.size));

        Bitmap thumb = imageData.thumb;
        ImageView imageView = holder.itemView.findViewById(R.id.imThumb);
        imageView.setImageBitmap(thumb);
    }
}
