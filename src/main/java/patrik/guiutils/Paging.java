package patrik.guiutils;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    private static final int PAGINATION_STEP = 3;

    private boolean nextEnabled;
    private boolean prevEnabled;
    private int pageSize;
    private int pageNumber;

    private List<PageItem> items = new ArrayList<>();

    public void addPageItems(int from, int to, int pageNumber) {
        for (int i = from; i < to; i++) {
            PageItem pageItem = new PageItem();
            pageItem.setActive(pageNumber != i);
            pageItem.setIndex(i);
            pageItem.setPageItemType(PageItemType.PAGE);
            items.add(pageItem);
        }
    }

    public void last(int pageSize) {
        PageItem pageItem = new PageItem();
        pageItem.setActive(false);
        pageItem.setPageItemType(PageItemType.DOTS);
        items.add(pageItem);

        PageItem pageItem1 = new PageItem();
        pageItem1.setActive(true);
        pageItem1.setIndex(pageSize);
        pageItem1.setPageItemType(PageItemType.PAGE);
        items.add(pageItem1);
    }

    public void first(int pageNumber) {
        PageItem pageItem = new PageItem();
        pageItem.setActive(pageNumber != 1);
        pageItem.setIndex(1);
        pageItem.setPageItemType(PageItemType.PAGE);
        items.add(pageItem);

        PageItem pageItem1 = new PageItem();
        pageItem1.setActive(false);
        pageItem1.setPageItemType(PageItemType.DOTS);
        items.add(pageItem1);
    }

    public static Paging of(int totalPages, int pageNumber, int pageSize) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setNextEnabled(pageNumber != totalPages);
        paging.setPrevEnabled(pageNumber != 1);
        paging.setPageNumber(pageNumber);

        if (totalPages < PAGINATION_STEP * 2 + 6) {
            paging.addPageItems(1, totalPages + 1, pageNumber);

        } else if (pageNumber < PAGINATION_STEP * 2 + 1) {
            paging.addPageItems(1, PAGINATION_STEP * 2 + 4, pageNumber);
            paging.last(totalPages);

        } else if (pageNumber > totalPages - PAGINATION_STEP * 2) {
            paging.first(pageNumber);
            paging.addPageItems(totalPages - PAGINATION_STEP * 2 - 2, totalPages + 1, pageNumber);

        } else {
            paging.first(pageNumber);
            paging.addPageItems(pageNumber - PAGINATION_STEP, pageNumber + PAGINATION_STEP + 1, pageNumber);
            paging.last(totalPages);
        }

        return paging;
    }

    public void setNextEnabled(boolean nextEnabled) {
        this.nextEnabled = nextEnabled;
    }

    public void setPrevEnabled(boolean prevEnabled) {
        this.prevEnabled = prevEnabled;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setItems(List<PageItem> items) {
        this.items = items;
    }
}