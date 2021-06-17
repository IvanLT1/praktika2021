package ai.sn.dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    public List<T> content;
    public Boolean hasNext;
    public Boolean hasPrevious;
    public Integer totalPages;
    public Integer currentPage;
}

