
# Simple Blog API Design
This is a guide to the API design for a simple blog system. 
The system consists of two types of users: User and Admin.




## User Permissions
### User

+ Has limited permissions.

+ Can perform actions such as viewing blog posts and commenting.

+ Can edit their own comments.

### Admin

+ Has elevated permissions but not unlimited power.

+ Cannot edit comments made by other users, as this could be seen as impersonation.

+ Can only delete comments if they violate guidelines or are inappropriate.
## Installation and Set up

Clone the repository to your local machine.
    
## API Endpoints
Below are the main API endpoints for the blog system:

+ GET /api/posts: Get all blog posts.

+ GET /api/posts/:postId: Get a specific blog post by ID.

+ POST /api/posts: Create a new blog post (User only).

+ PUT /api/posts/:postId: Update a blog post by ID (User only).

+ DELETE /api/posts/:postId: Delete a blog post by ID.

+ POST /api/comments/:postId: Add a comment to a blog post (User only).

+ PUT /api/comments/:commentId: Edit a comment (User only).

+ DELETE /api/comments/:commentId: Delete a comment.

+ POST /api/login: Log in as a user or admin.
## Authentication and Authorization
The API uses token-based authentication. Users and admins must include a valid token in the request headers to access protected endpoints.

+ To log in, send a POST request to /api/login with the user's credentials. The server will respond with a token.

+ For user actions (like adding comments), the token must belong to a logged-in user.

+ For admin actions (like creating or editing blog posts), the token must belong to an admin.
## Conclusion
This README provides a brief overview of the simple blog API system, including user types, permissions, and endpoints. 

For more details and implementation, please refer to the codebase and API documentation.

 Feel free to extend and customize the API as per your specific requirements. Happy coding!