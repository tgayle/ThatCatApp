package app.endershrooms.thatcatapp.net;

import app.endershrooms.thatcatapp.model.Breed;
import app.endershrooms.thatcatapp.model.Cat;
import app.endershrooms.thatcatapp.model.Category;
import app.endershrooms.thatcatapp.model.Favorite;
import app.endershrooms.thatcatapp.model.RequestResult;
import app.endershrooms.thatcatapp.model.Vote;
import app.endershrooms.thatcatapp.model.builders.FavoriteRequest;
import app.endershrooms.thatcatapp.model.builders.VoteRequest;
import io.reactivex.Single;
import java.util.List;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface CatService {

  @GET("breeds")
  Single<List<Breed>> getBreeds();

  @GET("breeds")
  Single<List<Breed>> searchBreeds(@Query("q") String query);

  @GET("categories")
  Single<List<Category>> getCategories();

  @GET("votes")
  Single<Vote> getVote(@Query("vote_id") String id);

  @GET("votes")
  Single<List<Vote>> getVotes();

  @POST("votes")
  Single<RequestResult> createVote(@Body VoteRequest vote);

  @DELETE("votes")
  Single<RequestResult> deleteVote(@Query("vote_id") String id);

  @GET("favourites")
  Single<List<Favorite>> getFavorites(@Query("sub_id") String id,
      @Query("limit") int limit,
      @Query("page") int page);

  @GET("favourites/{favourite_id}")
  Single<Favorite> getFavorite(@Query("sub_id") String id,
      @Path("favourite_id") String favoriteId);

  @DELETE("favourites/{favourite_id}")
  Single<RequestResult> deleteFavorite(@Query("sub_id") String id,
      @Path("favourite_id") String favoriteId);

  @POST("favourites")
  Single<RequestResult> createFavorite(@Body FavoriteRequest favorite);

  @GET("images/search")
  Single<List<Cat>> getImages(@QueryMap Map<String, String> queries);

  @GET("images/{image_id}")
  Single<RequestResult> getImage(@Path("image_id") String id);
}