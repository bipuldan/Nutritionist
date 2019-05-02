import { Component, OnInit } from '@angular/core';
import { Food } from '../../food';
import { FoodService } from '../../food.service';

@Component({
  selector: 'food-food-search',
  templateUrl: './food-search.component.html',
  styleUrls: ['./food-search.component.css']
})
export class FoodSearchComponent implements OnInit {

  foods: Array<Food>;

  constructor(private service: FoodService) { }

  ngOnInit() {
  }

  onEnter(searchKey) {
    this.service.searchFood(searchKey).subscribe(foods => {
      console.log(foods);
      this.foods = foods;

    })
  }
}
