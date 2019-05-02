import { Component, OnInit, Input } from '@angular/core';
import { FoodService } from '../../food.service';
import { Food } from '../../food';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Nutrient } from '../../nutrient';
import { Router } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';


@Component({
  selector: 'food-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {

  @Input()
  foods: Array<Food>;

  @Input()
  useFavouritelistApi: boolean;

  nutrientList: Array<Nutrient>;

  constructor(private service: FoodService, private snackBar: MatSnackBar, private route: Router) { }

  ngOnInit() {

  }

  getNutritionValue(ndbno) {
    console.log('ndbno is::', ndbno);
    this.service.getReport(ndbno).subscribe((data: any) => {
      this.nutrientList = data;
      this.service.gettingTheNutrients(this.nutrientList);
      console.log(this.nutrientList);
      this.route.navigate(['foods/nutrient']);
    });
  }

  addToFavouritelist(food) {
    const message = `${food.name} +  Food added to your Favouritelist`;
    console.log('added food::', food);
    this.service.addFoodToFavouritelist(food).subscribe((food) => {
      this.snackBar.open(message, '', {
        duration: 1000
      });
    });
  }

  deleteFoodFromFavouritelist(food) {
    this.service.deleteFoodFromFavouritelist(food).subscribe((result) => {
      const message = `${food.name} successfully deleted`;
      this.snackBar.open(message, '', {
        duration: 1000
      });
      const index = this.foods.indexOf(food);
      this.foods.splice(index, 1);
    });

  }

  updateFavouritelist(food) {
    this.service.updateFavouritelist(food).subscribe((updatedFood) => {

      const message = `${food.name} + Comment is updated successfully`;
      this.snackBar.open(message, '', {
        duration: 1000
      });
    });
  }
}
