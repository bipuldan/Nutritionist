import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Food } from '../../food';
import { FoodDialogComponent } from '../food-dialog/food-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { Nutrient } from '../../nutrient';

@Component ({
  selector: 'food-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  food: Food;

  @Input()
  nutrient: Nutrient;

  @Input()
  useFavouritelistApi: boolean;

  @Output()
  getNutrients = new EventEmitter();

  @Output()
  addFood = new EventEmitter();

  @Output()
  deleteFood = new EventEmitter();

  constructor(private dialog: MatDialog) { }

  ngOnInit() { }

  getNutrientValues(){
    this.getNutrients.emit(this.food.ndbno);
    console.log(this.food.ndbno);
  }

  addToFavouritelist() {
    this.addFood.emit(this.food);
  }

  deleteFromFavouritelist() {
    this.deleteFood.emit(this.food);
  }

  updateFavouritelist(actionType) {
    const dialogRef = this.dialog.open(FoodDialogComponent, {
      width: '400px',
      data: {obj: this.food, actionType: actionType}
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }
}

