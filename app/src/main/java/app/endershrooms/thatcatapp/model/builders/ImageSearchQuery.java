package app.endershrooms.thatcatapp.model.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageSearchQuery {

  private ImageSize size = ImageSize.MED;
  private SearchQueryOrder order = SearchQueryOrder.ASC;
  private int limit;
  private int page;
  private List<Integer> categoryIds = new ArrayList<>();
  private String breedId = "";

  public ImageSearchQuery() {
  }

  public ImageSearchQuery(ImageSize size, SearchQueryOrder order, int limit, int page,
      List<Integer> categoryIds, String breedId) {
    this.size = size;
    this.order = order;
    this.limit = limit;
    this.page = page;
    this.categoryIds = categoryIds;
    this.breedId = breedId;
  }

  public HashMap<String, String> toMap() {
    HashMap<String, String> map = new HashMap<>();

    StringBuilder categoryIdStr = new StringBuilder();

    for (Integer category : categoryIds) {
      categoryIdStr.append(categoryIdStr).append(",");
    }

    map.put("size", size.size);
    map.put("order", order.toString());
    map.put("limit", limit + "");
    map.put("page", page + "");
    map.put("category_ids", categoryIdStr.toString());
    map.put("breed_id", breedId);

    return map;
  }

  public ImageSize getSize() {
    return size;
  }

  public SearchQueryOrder getOrder() {
    return order;
  }


  public int getLimit() {
    return limit;
  }


  public int getPage() {
    return page;
  }


  public List<Integer> getCategoryIds() {
    return categoryIds;
  }


  public String getBreedId() {
    return breedId;
  }

  public ImageSearchQuery setSize(ImageSize size) {
    this.size = size;
    return this;
  }

  public ImageSearchQuery setOrder(
      SearchQueryOrder order) {
    this.order = order;
    return this;
  }

  public ImageSearchQuery setLimit(int limit) {
    this.limit = limit;
    return this;
  }

  public ImageSearchQuery setPage(int page) {
    this.page = page;
    return this;
  }

  public ImageSearchQuery setCategoryIds(List<Integer> categoryIds) {
    this.categoryIds = categoryIds;
    return this;
  }

  public ImageSearchQuery setBreedId(String breedId) {
    this.breedId = breedId;
    return this;
  }
}

