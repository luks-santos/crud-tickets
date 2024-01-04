import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Category } from 'src/app/model/category';
import { Comment } from 'src/app/model/comment';
import { Topic } from 'src/app/model/topic/topic';
import { InputDialogComponent } from 'src/app/shared/components/input-dialog/input-dialog.component';

import { CategoriesService } from '../../service/categories.service';
import { CommentService } from '../../service/comments.service';
import { TopicService } from '../../service/topic.service';

@Component({
  selector: 'app-ticket-dialog',
  templateUrl: './ticket-dialog.component.html',
  styleUrls: ['./ticket-dialog.component.scss']
})
export class TicketDialogComponent implements OnInit{

	form!: FormGroup;
	comments: Comment[] = [];
	categories: Category[] = [];
	topics: Topic[] = [];

	constructor(
		public dialogRef: MatDialogRef<InputDialogComponent>,
		@Inject(MAT_DIALOG_DATA) public data: any,
		private fb: FormBuilder,
		private commentService: CommentService,
		private categoryService: CategoriesService,
		private topicService: TopicService
	) { }
	
	ngOnInit() {
		this.form = this.fb.group({
			selectComments: [null, Validators.required],
			selectCategories: [null, Validators.required],
			selectTopics: [null, Validators.required],
			priority: [null, Validators.required],
		});
		this.getComments();
		this.getCategories();

		this.form.controls['selectCategories'].valueChanges.subscribe((selectedCategory) => {
			this.getTopicsByCategoryId(selectedCategory);
		});
	}

	getComments() {
		this.commentService.findAll().subscribe((comments) => {
			this.comments = comments;
		});
	}

	getCategories() {
		this.categoryService.findAll().subscribe((categories) => {
			this.categories = categories;
		});
	}

	getTopicsByCategoryId(categoryId: number) {
		this.topicService.findTopicsByCategoryId(categoryId).subscribe((topics) => {
			this.topics = topics;
			console.log(topics);
			
		});
	}
	
	close() {
		this.dialogRef.close();
	}

	save() {
		if (this.form.valid) {
			this.dialogRef.close(this.form.value);
		} 
		else {
			this.form.markAllAsTouched();
		}
	}
}
