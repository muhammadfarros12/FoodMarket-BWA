package com.farroos.foodmarket.model.response.login


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
        @Expose
        @SerializedName("address")
        val address: Any,
        @Expose
        @SerializedName("city")
        val city: Any,
        @Expose
        @SerializedName("created_at")
        val createdAt: Int,
        @Expose
        @SerializedName("current_team_id")
        val currentTeamId: String,
        @Expose
        @SerializedName("email")
        val email: String,
        @Expose
        @SerializedName("email_verified_at")
        val emailVerifiedAt: Any,
        @Expose
        @SerializedName("houseNumber")
        val houseNumber: Any,
        @Expose
        @SerializedName("id")
        val id: Int,
        @Expose
        @SerializedName("name")
        val name: String,
        @Expose
        @SerializedName("phoneNumber")
        val phoneNumber: Any,
        @Expose
        @SerializedName("profile_photo_path")
        val profilePhotoPath: Any,
        @Expose
        @SerializedName("profile_photo_url")
        val profilePhotoUrl: String,
        @Expose
        @SerializedName("roles")
        val roles: String,
        @Expose
        @SerializedName("updated_at")
        val updatedAt: Int
)