package patrik.guiutils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Page<T> {
    List<T> content;
    int totalPages;

    public Page(T paged, T totalPages) {
        content = new ArrayList<>();
        content.add(paged);
        totalPages = totalPages;
    }
}