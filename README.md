# projekt
Aplikacja opracowana w tym projekcie to aplikacja pogodowa, służąca do poinformowaniu użytkownika o bieżącej pogodzie w ich otoczeniu.
Używająć usługi API ze strony freegeoip.app, aplikacja pobiera dane lokacji użytkownika, opierając się na długości i szerokości geograficznej. 

Te dane są następnie podawane do funkcji która, za pomocą API udostępnianego przez usługę 7timer.info, pobiera dane temperatury oraz prędkości wiatru w podanej lokacji. API od 7timer.info dzieli bloki danych w 3-godzinnych odstępach, a aplikacja wybiera dane najbliższe obecnej godzinie, priorytezując dane z godzin przeszłych.

Aplikacja okienkowa posiada pięć pól w których pojawiają się wartości oraz dwa przyciski. Przycisk "Pobierz Lokację" pobiera wyłącznie dane lokalizacji, podczas gdy "Pobierz Dane" pobiera zarówno dane lokacji oraz dane pogodowe. Pobieranie danych pogodych zajmuje pewien okres czasu, dlatego okienko dialogowe wyskakuje jeżeli przycisk "Pobierz Dane" zostanie naciśnięty przed ukończeniem pobierania danych. 

