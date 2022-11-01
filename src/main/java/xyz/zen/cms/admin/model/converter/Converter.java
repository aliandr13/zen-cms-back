package xyz.zen.cms.admin.model.converter;

public interface Converter<Entity, Dto> {

    Dto convert(Entity entity);
}
