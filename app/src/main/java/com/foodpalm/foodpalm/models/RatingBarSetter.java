package com.foodpalm.foodpalm.models;

import android.widget.ImageView;

import com.foodpalm.foodpalm.R;

/**
 * Created by User on 3/17/2017.
 */

public class RatingBarSetter {
    public RatingBarSetter() {
    }

    public void setRatingStar(double rating, ImageView star1, ImageView star2, ImageView star3, ImageView star4, ImageView star5){

        if(rating < 1 && rating > 0){
            star1.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        else if(rating < 1.5 && rating >= 1){
            star1.setImageResource(R.drawable.ic_star_black_24dp);
        }
        else if(rating < 2 && rating >= 1.5){
            star1.setImageResource(R.drawable.ic_star_black_24dp);
            star2.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        else if(rating < 2.5 && rating >= 2){
            star1.setImageResource(R.drawable.ic_star_black_24dp);
            star2.setImageResource(R.drawable.ic_star_black_24dp);
        }
        else if(rating < 3 && rating >= 2.5){
            star1.setImageResource(R.drawable.ic_star_black_24dp);
            star2.setImageResource(R.drawable.ic_star_black_24dp);
            star3.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        else if(rating < 3.5 && rating >= 3){
            star1.setImageResource(R.drawable.ic_star_black_24dp);
            star2.setImageResource(R.drawable.ic_star_black_24dp);
            star3.setImageResource(R.drawable.ic_star_black_24dp);
        }
        else if(rating < 4 && rating >= 3.5){
            star1.setImageResource(R.drawable.ic_star_black_24dp);
            star2.setImageResource(R.drawable.ic_star_black_24dp);
            star3.setImageResource(R.drawable.ic_star_black_24dp);
            star4.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        else if(rating < 4.5 && rating >= 4){
            star1.setImageResource(R.drawable.ic_star_black_24dp);
            star2.setImageResource(R.drawable.ic_star_black_24dp);
            star3.setImageResource(R.drawable.ic_star_black_24dp);
            star4.setImageResource(R.drawable.ic_star_black_24dp);
        }
        else if(rating < 5 && rating >= 4.5){
            star1.setImageResource(R.drawable.ic_star_black_24dp);
            star2.setImageResource(R.drawable.ic_star_black_24dp);
            star3.setImageResource(R.drawable.ic_star_black_24dp);
            star4.setImageResource(R.drawable.ic_star_black_24dp);
            star5.setImageResource(R.drawable.ic_star_half_black_24dp);
        }
        else if(rating >= 5){
            star1.setImageResource(R.drawable.ic_star_black_24dp);
            star2.setImageResource(R.drawable.ic_star_black_24dp);
            star3.setImageResource(R.drawable.ic_star_black_24dp);
            star4.setImageResource(R.drawable.ic_star_black_24dp);
            star5.setImageResource(R.drawable.ic_star_black_24dp);
        }
    }
}
