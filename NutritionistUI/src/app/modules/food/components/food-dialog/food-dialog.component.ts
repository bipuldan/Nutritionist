import { Component, OnInit, Inject } from '@angular/core';
import { Food } from '../../food';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FoodService } from '../../food.service';

@Component({
  selector: 'food-food-dialog',
  templateUrl: './food-dialog.component.html',
  styleUrls: ['./food-dialog.component.css']
})
export class FoodDialogComponent implements OnInit {

  food: Food;
  comments: string;
  actionType: string;

  constructor(private snacBar: MatSnackBar, private dialogRef: MatDialogRef<FoodDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any, private service: FoodService) {

    this.comments = data.obj.comments;
    this.food = data.obj;
    this.actionType = data.actionType;
  }

  ngOnInit() {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  updateComments() {
    this.food.comments = this.comments;
    this.dialogRef.close();
    this.service.updateFavouritelist(this.food).subscribe(() => {
      this.snacBar.open('Comment updated successfully', '', {
        duration: 2000
      });
    });
  }
}
