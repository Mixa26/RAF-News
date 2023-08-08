# RAF-News

This is a university project of a full-stack website for managing news. It has a dedicated CMS for two types of users (Admin/Content-Creator). 
Both Admins and Content-Creators can add News, Categories (which have News), update them, delete them. The difference between this two users is that Admins
can also do these operations on Users.<br>
<br>
Admins can also deactive certain CMS users (not other admins). <br>
<br>
The full documentation is in the "Web programiranje.docx" file.
<br>
The back-end of this page was made using java, JAX-RS with a TomCat server.<br>
The front-end was made in Vue 2 with the help of Bootstrap.<br>
<br>
Here are the corresponding pages for the CMS:<br>
<br>
Login:
![login](pictures/Login.jpg)

Home page (as you can see there is a tab button for users because i've loged in with a Admin account):
![HomePage](pictures/HomePage.jpg)

Categories page:
![Categories](pictures/Categories.jpg)

Add new category(same as change category):
![NewCategory](https://github.com/Mixa26/RAF-News/assets/71144280/a63312c1-8b46-4167-af64-85dcc4ead3fa)

News page:
![News](pictures/News.jpg)

Add news page(same as change news):
![NewNews](pictures/NewNews.jpg)

And then we have the users platform where people can read news, find the most read ones or find the news in a given category:
![HomePageUsers](pictures/HomePageUsers.jpg)

Most read news in the last 30 days:
![MostReadNews](pictures/HomePageUsers.jpg)

News sorted by category:
![CategoryNews](pictures/CategoryNews.jpg)

Detailed view of the news, people can also add comments here without loging in:
![DetailedNews](pictures/DetailedNews.jpg)

If you click on any of the tags on the detailed view of the news it will take you to all the news with that tag:
![TagNews](pictures/TagNews.jpg)
