# PopularHashtags

PopularHashtags is a Spark streaming application which reads in real time Twitter tweets and calculates the most popular hashtags. 

Application stores popular hastags in 5 minutes windows. That means that list of popular hashtags is created over the last 5 minutes. 

Please create a `credentials.properties` file in order to run the code. Following keys should be specified in credentials file:
- Consumer Key
- Consumer Secret
- Access Token
- Access Token Secret

Execution demo video: [youtube link](https://youtu.be/YrLZb9bbrwE)

PopularHashtags was developed as part of `Object-oriented programming` course.