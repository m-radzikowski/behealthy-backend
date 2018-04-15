insert into users.User set id = 1, login = "maciej", password = "qwerty", availableChests = 10, gold = 2000,
  lvl = 0, exp = 0, lastTimeUpdated = now(), endoId = 36566507,
  cookie = "_03CF335F36B39C6100000162C5C494E9RQt20a22uIchRNJxUxcgwgZIXq30vL99Yz4n2r%2Bk3mxC5vecBfYFM3pY7mNiOrvb218K673VwK6Y%2BZCOVxhGfNKQzn4iApz7D896r8MBrts%3D"
;

insert into quests.Quest set id = 1, date = now(), title = "Rowerowy   wyscig", description = "Czy   zabrales   ze   soba   rower?", type = "BIKE", value = 10, exp = 150;
insert into quests.Quest set id = 2, date = now(), title = "Siedmiokilometrowe   buty", description = "Dogon   swoje   przeznaczenie", type = "RUN", value = 7, exp = 250;
insert into quests.Quest set id = 3, date = now(), title = "Burn   it   down", description = "Kalorie   -   czy   je   spalisz?", type = "KCAL", value = 400, exp = 200;

insert into shop.Coupon set id = 1, title = "Rabat   na   silownie", description = "Z   tym   kuponem   wejscie   na   silownie   w   naszej   sieci   partnerskiej   5%   taniej", gold = 1500;
insert into shop.Coupon set id = 2, title = "Sok   w   BIOWAY", description = "Odbierz   darmowy   sok   do   posilku   w   BIOWAY", gold = 5300;
insert into shop.Coupon set id = 3, title = "Tansze   zdrowie", description = "Znizka   10%   na   owoce   i   warzywa   w   sklepach   Carrefour", gold = 4500;
