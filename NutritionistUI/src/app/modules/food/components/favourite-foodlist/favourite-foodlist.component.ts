import { Component, OnInit } from '@angular/core';
import { FoodService } from '../../food.service';
import { Food } from '../../food';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'food-favourite-foodlist',
  templateUrl: './favourite-foodlist.component.html',
  styleUrls: ['./favourite-foodlist.component.css']
})
export class FavouriteFoodlistComponent implements OnInit {

  foods: Array<Food>;
  usefavouritelistApi: boolean = true;

  constructor(private service: FoodService, private snackBar: MatSnackBar) {
    this.foods = [];
  }

  ngOnInit() {
    this.service.getFavouriteListedFood().subscribe(foods => {
      console.log(foods);
      if (foods.length == 0) {
        this.snackBar.open('You have no favourite food added, please add and visit again', '', {
          duration: 3000
        });
      }
      this.foods.push(...foods);
    });
  }

}
