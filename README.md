# CW-Task1

Android Technical Round
Instructions:
1. Notify the HR when you start and end the test.
2. Email the zipped folder containing your project files to the HR.
3. We would review not just the result, but also the coding style, architecture and necessary
   commenting. Code cleanliness would impact our decision making.
4. While sharing the code, please make sure you replace the API Key with a placeholder so
   as to keep your personal keys unexposed.
5. It may be possible that below information be partially incomplete on purpose. You can apply your
   decision making skills to understand and accomplish the task.

 Scenario:
   With the use of Yelp Business API, find the nearby restaurants.
7. You may default the search location to “New York City”, but usage of current location is
   recommended.
8. There’s a radius slider which allows you to change the radius of search. Limits are 100m
   to 5000m.
9. Apply a pull to refresh so as to reload the results.
10. Apply pagination with max 15 per page results.
11. It's best to plot the search results by distance.
12. The required API key can be obtained by signing up with Yelp and creating an app.
13. The bolded words above are the keywords that would be useful in the search:
    term = “restaurants’,
    location = “NYC” or “New York City”,
    radius = 500 (Int value in meters),
    sort_by = distance.
    limit = 15


curl --location --request GET 'https://api.yelp.com/v3/businesses/search?term=restaurants&latitude=37.786882&longitude=-122.399972&sort_by=distance&limit=60&radius=500' \
--header 'Authorization: Bearer XPFgzKwZGK1yqRxHi0d5xsARFOLpXIvccQj5jekqTnysweGyoIfVUHcH2tPfGq5Oc9kwKHPkcOjk2d1Xobn7aTjOFeop8x41IUfVvg2Y27KiINjYPADcE7Qza0RkX3Yx'
