# Technical Test Outline
- Build domain objects (UI not required) to model a shopping cart system with the following
  acceptance criteria:
- Multiple products can be purchased in a single basket. (the products can be whatever you wish)
- Products should belong to a Category
  We also want to support the following discounts/promotions:
- A general basket % discount (eg 10% off total basket)
- A discount for a specific product (eg 20% off all apples)
- A buy 2 get 1 free promotion (eg buy 2 apples, get 3rd apple free)
- A combination purchase deal (eg buy 1 sandwich and 1 apple and 1 drink for £X price)

**Only one discount can be applied to a product.**
For example: if a product is included in a meal deal or buy 2 get 1 free promotion then it should
not also be discounted with a general basket discount.

Please use **Kotlin** as the programming language and include unit tests, please also try to be
aware of edge cases you might need to consider.

This is a general engineering test so giving thought to maintainability and extensibility is key.

# Work Done

## Summary

The work has been completed entirely in Kotlin with a very basic Compose UI.
Each item in the list can be added to a basket, with the applied discounts, and items in each
discount, listed at the bottom of the page.

## Issues Hit

One issue that I hit while creating the application was that I was not copying the Product that was
being added to the list, meaning that every single instance of a Coffee Beans (12oz) in the list was
the exact same object.

I overcame this by using Gson to quickly serialize and then deserialize the object into a new
instance.

## Discounts Applied

- Buy 2 Get 1 Free on Coffee Beans (12 oz)
- Product Discount 25% Off on Pasta (1 lb)
- Combination Price £5.00 on Cage-Free Eggs (Dozen), Sliced Bread (Whole Wheat), Ground Beef (1 lb)
- General Basket Discount 5% Off