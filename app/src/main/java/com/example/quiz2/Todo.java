package com.example.quiz2;

class Todo {
    String id;
    String title;
    String userId;
    String completed;

    Todo(String id, String title, String userId, String completed) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.completed = completed;
    }

    String getId() {
        return "id: " + id;
    }

    String getTitle() {
        return "title: " + title;
    }

    String getUserId() {
        return "userId: " + userId;
    }

    String getCompleted() {
        return "completed: " + completed;
    }
}