import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Embeds extends ListenerAdapter {       //sei mal leise

    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentStripped().startsWith(main.prefix+ "si")) {
            event.getChannel().sendMessageEmbeds(serverinfo(event.getGuild()).build()).queue();
        }
    }

    private static EmbedBuilder serverinfo(Guild guild) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        OffsetDateTime date = guild.getTimeCreated();
        String formatted = df.format(date);

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.decode("#8800FF"));
        builder.setTitle(guild.getName());
        builder.setDescription("Allgemeine Informationen über den Server");
        builder.addField("Member:", String.valueOf(guild.getMemberCount()), true);
        builder.addField("Erstellt:", formatted, true);
        builder.addField("Owner:", guild.getOwner().getUser().getName(), true);

        return builder;
    }
}
