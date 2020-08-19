USE olympics;

SELECT `Name` FROM olympics.athlete_events WHERE athlete_events.Sport = 'Fencing' ORDER BY athlete_events.`Name` ASC;