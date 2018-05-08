package lyhoangvinh.com.myutil.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseHeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_FOOTER = 2;
    protected static final int TYPE_NON_FOOTER_HEADER = -1;

    private List<View> headers = new ArrayList<>();
    private List<View> footers = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return createHeaderViewHolder(parent);
        } else if (viewType == TYPE_FOOTER) {
            return createFooterViewHolder(parent);
        } else {
            return createHolder(parent, viewType);
        }
    }

    protected abstract RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType);

    /**
     * Create view holder for header
     * Override this method for custom header footer View Holder
     * @param parent parent view group (e.g recyclerView)
     * @return instance of {@link HeaderFooterViewHolder}
     */
    protected HeaderFooterViewHolder createHeaderViewHolder(ViewGroup parent) {
        return SimpleHeaderFooterViewHolder.createInstance(parent);
    }

    /**
     * Create view holder for footer
     * Override this method for custom header footer View Holder
     * @param parent parent view group (e.g recyclerView)
     * @return instance of {@link HeaderFooterViewHolder}
     */
    protected HeaderFooterViewHolder createFooterViewHolder(ViewGroup parent) {
        return SimpleHeaderFooterViewHolder.createInstance(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = holder.getItemViewType();
        if (type == TYPE_HEADER) {
            View v = headers.get(position);
            //add our view to a header view and display it
            bindHeaderFooter((HeaderFooterViewHolder) holder, v);
        } else if (type == TYPE_FOOTER) {
            View v = footers.get(position - headers.size() - getItemCountExceptHeaderFooter());
            //add oru view to a footer view and display it
            bindHeaderFooter((HeaderFooterViewHolder) holder, v);
        } else {
            // item
            bindHolder(holder, position);
        }
    }

    private void bindHeaderFooter(HeaderFooterViewHolder vh, View view) {
        //empty out our FrameLayout and replace with our header/footer
        if (view != null) {
            vh.bindView(view);
        }
    }

    protected abstract void bindHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return headers.size() + footers.size() + getItemCountExceptHeaderFooter();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < headers.size()) {
            return TYPE_HEADER;
        } else if (position >= headers.size() + getItemCountExceptHeaderFooter()) {
            return TYPE_FOOTER;
        }
        return TYPE_NON_FOOTER_HEADER;
    }

    /**
     * @return number items of adapter data except header and footer
     */
    public abstract int getItemCountExceptHeaderFooter();

    public int getRealItemPosition(int adapterPosition) {
        return adapterPosition - headers.size();
    }

    protected boolean isHeaderOrFooter(int itemViewType) {
        return itemViewType == TYPE_HEADER || itemViewType == TYPE_FOOTER;
    }

    //add a header to the adapter
    public void addHeader(View header) {
        if (header != null && !headers.contains(header)) {
            headers.add(header);
            //animate
            notifyItemInserted(headers.size() - 1);
        }
    }

    //add a header at top of adapter
    public void addHeaderFirst(View header) {
        if (header != null && !headers.contains(header)) {
            headers.add(0, header);
            //animate
            notifyItemInserted(0);
        }
    }

    /**
     * remove a header from the adapter
     */
    public void removeHeader(View header) {
        if (header != null && headers.contains(header)) {
            //animate
            notifyItemRemoved(headers.indexOf(header));
            headers.remove(header);
            if (header.getParent() != null) {
                ((ViewGroup) header.getParent()).removeView(header);
            }
        }
    }

    /**
     * add a footer to the adapter
     * @param footer view to add
     */
    public void addFooter(View footer) {
        if (footer != null && !footers.contains(footer)) {
            footers.add(footer);
            //animate
            notifyItemInserted(headers.size() + getItemCountExceptHeaderFooter() + footers.size() - 1);
        }
    }

    /**
     * remove a footer from the adapter
     * @param footer view to remove
     */
    public void removeFooter(View footer) {
        if (footer != null && footers.contains(footer)) {
            //animate
            notifyItemRemoved(headers.size() + getItemCountExceptHeaderFooter() + footers.indexOf(footer));
            footers.remove(footer);
            if (footer.getParent() != null) {
                ((ViewGroup) footer.getParent()).removeView(footer);
            }
        }
    }

    public List<View> getHeaders() {
        return headers;
    }

    public List<View> getFooters() {
        return footers;
    }

    public static abstract class HeaderFooterViewHolder extends RecyclerView.ViewHolder{

        public HeaderFooterViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindView(View view);
    }

    //our header/footer RecyclerView.ViewHolder is just a FrameLayout
    private static class SimpleHeaderFooterViewHolder extends HeaderFooterViewHolder {

        static HeaderFooterViewHolder createInstance(ViewGroup parent) {
            //create a new framelayout, or inflate from a resource
            FrameLayout frameLayout = new FrameLayout(parent.getContext());
            //make sure it fills the space
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            );
            return new SimpleHeaderFooterViewHolder(frameLayout);
        }

        FrameLayout base;

        SimpleHeaderFooterViewHolder(View itemView) {
            super(itemView);
            this.base = (FrameLayout) itemView;
        }

        @Override
        public void bindView(View view) {
            base.removeAllViews();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.gravity = Gravity.CENTER_HORIZONTAL;
            if (view.getLayoutParams() != null) {
                params.height = view.getLayoutParams().height;
                params.width = view.getLayoutParams().width;
            }
            ViewGroup parent = (ViewGroup)view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            base.addView(view, params);
        }
    }
}
