import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContainerComponent } from './components/container/container.component';
import { FoodSearchComponent } from './components/food-search/food-search.component';
import { FavouriteFoodlistComponent } from './components/favourite-foodlist/favourite-foodlist.component';
import { FoodDialogComponent } from './components/food-dialog/food-dialog.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { SharedModule } from '../shared/shared.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FoodService } from './food.service';
import { FoodRouterModule } from './food-router.module';
import { TokenInterceptorService } from './token-interceptor.service';
import { NutritionDialogComponent } from './components/nutrition-dialog/nutrition-dialog.component';

@NgModule({
  declarations:
  [ContainerComponent,
    FoodSearchComponent,
    FavouriteFoodlistComponent,
    FoodDialogComponent,
    ThumbnailComponent,
    NutritionDialogComponent,
  ],

  imports:
  [
    CommonModule,
    SharedModule,
    HttpClientModule,
  ],
  entryComponents: [FoodDialogComponent],
  exports: [
    FoodRouterModule,
    ThumbnailComponent
  ],
  providers: [FoodService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ]
})
export class FoodModule { }
