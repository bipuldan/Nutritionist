import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from './food';
import { retry, map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { Nutrient } from './nutrient';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  ndbEndpoint: string;
  reportPrefix: string;
  apiKey: string;
  foodlistEndpoint: string;
  searchEndipoint: string;
  nutrientList: Array<Nutrient>;

  constructor(private http: HttpClient) {
    this.apiKey = 'api_key=UpKq5b8WkpTLIMTrfxw2Fn76ET7bOFd36ifNVrq2';
    this.ndbEndpoint = 'https://api.nal.usda.gov/ndb';
    this.foodlistEndpoint = 'http://localhost:8088/api/v1/foodservice/food';
   }

  searchFood(searchKey: string, quantity: number = 25): Observable<Array<Food>> {
    console.log('service is hitted');
    if (searchKey.length > 0) {
      const searchEndpoint = `${this.ndbEndpoint}/search/?format=json&q=${searchKey}&sort=n&max=${quantity}&offset=0&${this.apiKey}`;
      return this.http.get(searchEndpoint).pipe(
        retry(3),
        map(this.pickFoodResults)
      );
    }
  }

  getReport(ndbno: number){
    const reportPrefix = `${this.ndbEndpoint}/V2/reports?ndbno=${ndbno}&type=b&format=json&${this.apiKey}`;
    return this.http.get(reportPrefix).pipe(
      retry(3),
      map(this.pickFoodReport),
    );
  }

  addFoodToFavouritelist(food) {
    return this.http.post(this.foodlistEndpoint, food, {responseType: 'text'});
  }

  getFavouriteListedFood(): Observable<Array<Food>> {
    return this.http.get<Array<Food>>(this.foodlistEndpoint);
  }

  deleteFoodFromFavouritelist(food) {
    const delUrl = `${this.foodlistEndpoint}/${food.id}`;
    return this.http.delete(delUrl, {responseType: 'text'});
  }

  updateFavouritelist(food) {
    const delUrl = `${this.foodlistEndpoint}/${food.id}`;
    return this.http.put(delUrl, food);
  }

  pickFoodResults(response) {
    return response['list']['item'];
  }

  pickFoodReport(response){
    return response['foods']['0']['food']['nutrients'];
  }

  gettingTheNutrients(nutrientList: Array<Nutrient>){
    this.nutrientList= nutrientList;
  }

  sendingTheNutrientsValue(){
    return this.nutrientList;
  }

}
