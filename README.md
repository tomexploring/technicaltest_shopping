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

In order to make the application easily expandable I have made the objects pull in from a vararg of
JSON files, meaning that if we needed to add a new data source for Products, then you just need to
pass in the new address for this. This would be easily expandable to include pulling in from text
files etc if required/depending on the requirement of the customer.

The discounts themselves are also able to be created from JSON files or text files in the same way
however this hasn't been done in this application as this has been demonstrated using the Product
class.

I have created two modules to handle the functionality required:

1. **app** - simulates a front end that is importing and displaying products.
2. **product** - a library that is providing discounts and products to the UI.

## Discounts Applied

- General Basket Discount 5% Off
- Product Discount 25% Off on Pasta (1 lb)
- Buy 2 Get 1 Free on Coffee Beans (12 oz)
- Combination Price £5.00 on Cage-Free Eggs (Dozen), Sliced Bread (Whole Wheat), Ground Beef (1 lb)

## Issues Hit

One issue that I hit while creating the application was that I was not copying the Product that was
being added to the list, meaning that every single instance of a Coffee Beans (12oz) in the list was
the exact same object.

I overcame this by using Gson to quickly serialize and then deserialize the object into a new
instance.

## Unit Testing

I have created two unit testing files to handle the different unit tests for the classes that they
correspond to.
These unit tests are lightweight and can be run either when the application is being built or as
part of a process before commiting any code to the repository.

### DiscountTests

The DiscountTests are to ensure that the discounts are working as expected on Products when they are
in a Basket, I have added the different types of Discounts and Products to a Basket to simulate the
different behaviour here.

### ProductTests

As I had issues with the copying of the products while creating the application, I chose to add a
unit test specifically for this.

## Future Changes

If this was a library that I was producing as part of a larger project then I would consider
updating the UI to include tabs for categories, a way to clear the basket and payment options would
need to be considered. Finally there would need to be a way to then export the order details to have
this processed.

I would also move the test RAW data that I have used in this example into the library rather than
having it in the front end, as that is where the data needs to be used rather than in the UI module.

The AppliedDiscount class would need to be created so that a Triple was not being used for the
Discounts that were applied to the basket in realtime.

Finally I would add more UnitTests for each Type of Discount that could be used in the library, to
ensure that they were working as expected as this is such key functionality.