insert into users.User set id = 1, login = "maciej", password = "qwerty", availableChests = 10, gold = 2000,
  lvl = 0, exp = 0, lastTimeUpdated = now(), endoId = 36566507,
  cookie = "_1AEB03073D95835900000162C58672B6VQZFfUKxgeOaQ10V05uYzB2CDBefjAHWsB9QoK3twZMYH5vTNpU8FfVt3KJNsHQi9P9UX9SunfqyddPDZmcSUCQGu1rwOIjZQaVmcx8IHIU%3D"
;

insert into quests.Quest set id = 1, date = now(), title = "Rowerowy wyscig", description = "Czy zabrales ze soba rower?", type = "BIKE", value = 10;
insert into quests.Quest set id = 2, date = now(), title = "Siedmiokilometrowe buty", description = "Dogon swoje przeznaczenie", type = "RUN", value = 7;
insert into quests.Quest set id = 3, date = now(), title = "Burn it down", description = "Kalorie - czy je spalisz?", type = "KCAL", value = 400;

insert into shop.Coupon set id = 1, description = "Talon na balon", title = "Z tym kuponem mozesz odebrac darmowy balon w kazdym sklepie z balonami", gold = 1500;
insert into shop.Coupon set id = 2, description = "Kawa w BIOWAY", title = "Odbierz darmowa kawe do posilku w BIOWAY", gold = 5300;
insert into shop.Coupon set id = 3, description = "Lorem ipsum", title = "Pamietaj o uzupelnieniu tego opisu przed demo", gold = 4500;
