SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) as n
FROM corso c, iscrizione i
WHERE c.codins = i.codins
AND c.pd = 1
GROUP BY c.codins, c.crediti, c.nome, c.pd