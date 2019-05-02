import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';
import { AuthGuardService } from '../../auth-guard.service';
import { FoodSearchComponent } from './components/food-search/food-search.component';
import { FavouriteFoodlistComponent } from './components/favourite-foodlist/favourite-foodlist.component';
import { NutritionDialogComponent } from './components/nutrition-dialog/nutrition-dialog.component';


const foodRouter: Routes = [
  {
    path: 'foods',
    children: [
      {
        path: '',
        redirectTo: '/foods/foodSearch',
        pathMatch: 'full',
        canActivate: [AuthGuardService]
      },
      {
        path: 'foodSearch',
        component: FoodSearchComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'favouriteList',
        component: FavouriteFoodlistComponent,
        canActivate: [AuthGuardService],
       },
       {
        path: 'nutrient',
        component: NutritionDialogComponent,
        canActivate: [AuthGuardService],
      },
    ]
  }];

@NgModule({
      imports: [
        RouterModule.forChild(foodRouter)
      ],
      exports: [
        RouterModule
      ]
    })
    export class FoodRouterModule { }
