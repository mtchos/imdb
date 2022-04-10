package com.sousa.matheus;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {

    private final PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generate(List<Movie> movies) throws Exception {
        writer.println("<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "  <head>" +
                "    <meta charset=\"UTF-8\" />" +
                "    <meta" +
                "      name=\"viewport\"" +
                "      content=\"width=device-width, initial-scale=1, shrink-to-fit=no\"" +
                "    />" +
                "    <link" +
                "      rel=\"stylesheet\"" +
                "      href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"" +
                "      integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\"" +
                "      crossorigin=\"anonymous\"" +
                "    />" +
                "    <title>IMDb</title>" +
                "  </head>" +
                "  <body>");
        for (Movie movie : movies) {
            String movieDiv =
                "    <div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem\">" +
                "      <h4 class=\"card-header\">%s</h4>" +
                "      <div class=\"card-body\">" +
                "        <img class=\"card-img\" src=\"%s\" alt=\"%s\" />" +
                "        <p class=\"card-text mt-2\">Nota: %s - Ano %s</p>" +
                "      </div>" +
                "    </div>";
            writer.println(
                    String.format(
                            movieDiv, movie.getTitle(), movie.getImage(),
                            movie.getTitle(), movie.getImDbRating(), movie.getYear()));
        }
        writer.println(
                "  </body>" +
                "</html>");
    }
}
