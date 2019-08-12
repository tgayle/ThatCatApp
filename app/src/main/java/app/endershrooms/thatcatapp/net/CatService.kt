package app.endershrooms.thatcatapp.net

import app.endershrooms.thatcatapp.model.*
import app.endershrooms.thatcatapp.model.builders.FavoriteRequest
import app.endershrooms.thatcatapp.model.builders.VoteRequest
import io.reactivex.Single
import retrofit2.http.*

interface CatService {

  @GET("breeds")
  fun getBreeds(): Single<List<Breed>>

  @GET("breeds")
  fun searchBreeds(@Query("q") query: String): Single<List<Breed>>

  @GET("categories")
  fun getCategories(): Single<List<Category>>

  @GET("votes")
  fun getVote(@Query("vote_id") id: String): Single<Vote>

  @GET("votes")
  fun getVotes(): Single<List<Vote>>

  @POST("votes")
  fun createVote(@Body vote: VoteRequest): Single<RequestResult>

  @DELETE("votes")
  fun deleteVote(@Query("vote_id") id: String): Single<RequestResult>

  @GET("favourites")
  fun getFavorites(@Query("sub_id") id: String,
      @Query("limit") limit: Int,
      @Query("page") page: Int): Single<List<Favorite>>

  @GET("favourites/{favourite_id}")
  fun getFavorite(@Query("sub_id") id: String,
      @Path("favourite_id") favoriteId: String): Single<Favorite>

  @DELETE("favourites/{favourite_id}")
  fun deleteFavorite(@Query("sub_id") id: String,
      @Path("favourite_id") favoriteId: String): Single<RequestResult>

  @POST("favourites")
  fun createFavorite(@Body favorite: FavoriteRequest): Single<RequestResult>

  @GET("images/search")
  fun getImages(@QueryMap queries: Map<String, String>):  Single<List<Cat>>

  @GET("images/{image_id}")
  fun getImage(@Path("image_id") id: String)
}