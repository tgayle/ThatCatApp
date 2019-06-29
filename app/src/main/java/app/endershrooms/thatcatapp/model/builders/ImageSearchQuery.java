package app.endershrooms.thatcatapp.model.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageSearchQuery {

  private ImageSize size = ImageSize.MED;
  private Order order = Order.ASC;
  private int limit;
  private int page;
  private List<Integer> categoryIds = new ArrayList<>();
  private String breedId = "";

  public ImageSearchQuery() {
  }

  public ImageSearchQuery(ImageSize size, Order order, int limit, int page,
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

  public void setSize(ImageSize size) {
    this.size = size;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public List<Integer> getCategoryIds() {
    return categoryIds;
  }

  public void setCategoryIds(List<Integer> categoryIds) {
    this.categoryIds = categoryIds;
  }

  public String getBreedId() {
    return breedId;
  }

  public void setBreedId(String breedId) {
    this.breedId = breedId;
  }
}

enum Order {
  RANDOM,
  ASC,
  DESC
}