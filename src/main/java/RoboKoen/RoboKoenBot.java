package RoboKoen;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.EnumSet;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class RoboKoenBot extends ListenerAdapter {
    private static String accessKey = "MTA4MDYwMDkwNjEyODY5MTMzMg.GC-cJW.js6aV06hrSmvo0TbNqAxFqMpJ06HN2U1VV6dr8"; //Holy shit this is so unsafe, I gotta figure out how to do this correctly later.
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createLight(accessKey, EnumSet.noneOf(GatewayIntent.class)) // slash commands don't need any intents
                .addEventListeners(new RoboKoenBot())
                .build();

        CommandListUpdateAction commands = jda.updateCommands();

        String testCommandID = "test";
        commands.addCommands(
                Commands.slash(testCommandID, "Test to see if bot works.")

                //.setGuildOnly(true)
                //.setDefaultPermissions(DefaultMemberPermissions.DISABLED)
        );

        //add other commands here


        commands.queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // Only accept commands from guilds
        if (event.getGuild() == null)
            return;
        switch (event.getName())
        {
            case "test":
                koen(event); // content is required so no null-check here
                break;
            default:
                event.reply("I don't know what that command is :(").setEphemeral(true).queue();
        }
    }

    public void koen(SlashCommandInteractionEvent event) {
        event.reply("Koen!").setEphemeral(true).queue();
    }

}
