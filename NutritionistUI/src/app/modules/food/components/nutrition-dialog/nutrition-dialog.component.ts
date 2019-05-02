import { Component, OnInit, Inject } from '@angular/core';
import { FoodService } from '../../food.service';
import { Nutrient } from '../../nutrient';
import { Router } from '@angular/router';

@Component({
  selector: 'food-nutrition-dialog',
  templateUrl: './nutrition-dialog.component.html',
  styleUrls: ['./nutrition-dialog.component.css']
})
export class NutritionDialogComponent implements OnInit {

  constructor(private service: FoodService, private route: Router) {}
  displayedColumns: string[] = ['name', 'value', 'unit'];
  dataSource = [];
  ngOnInit() {
    this.dataSource = this.service.sendingTheNutrientsValue();
  }
  goBack(){
    this.route.navigate(['/foods/foodSearch']);
  }
}

