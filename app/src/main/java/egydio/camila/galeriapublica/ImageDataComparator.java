package egydio.camila.galeriapublica;

import androidx.recyclerview.widget.DiffUtil;

import org.checkerframework.checker.nullness.qual.NonNull;

class ImageDataComparator extends DiffUtil.ItemCallback<ImageData> {

    @Override
    public boolean areItemsTheSame(@NonNull ImageData oldItem, @NonNull ImageData newItem) {
        return oldItem.uri.equals(newItem.uri);
    }

    @Override
    public boolean areContentsTheSame(@NonNull ImageData oldItem,  @NonNull ImageData newItem) {
        return oldItem.uri.equals(newItem.uri);
        }
}

