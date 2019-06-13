
// some cases the backslash mess up you can use this fo unexpected backslashes in paths 
if(patharchivo_excel.contains("\\\\"))
{	
	log.info("problema de backslashes----> position"+patharchivo_excel.indexOf("\\\\"));
	patharchivo_excel=patharchivo_excel.replaceAll(Pattern.quote("\\\\"),Matcher.quoteReplacement("/"));
}

