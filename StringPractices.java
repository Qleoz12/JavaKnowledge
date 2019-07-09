
// some cases the backslash mess up you can use this fo unexpected backslashes in paths 
if(patharchivo_excel.contains("\\\\"))
{	
	log.info("problema de backslashes----> position"+patharchivo_excel.indexOf("\\\\"));
	patharchivo_excel=patharchivo_excel.replaceAll(Pattern.quote("\\\\"),Matcher.quoteReplacement("/"));
}


// string to boolean cool way  and extensibble 
private boolean convertToBoolean(String value) {
    boolean returnValue = false;
    if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || 
        "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
        returnValue = true;
    return returnValue;
}
